package com.jobappms.jobms.job.impl;

import com.jobappms.jobms.job.Job;
import com.jobappms.jobms.job.JobRepository;
import com.jobappms.jobms.job.JobService;
import com.jobappms.jobms.job.clients.CompanyClient;
import com.jobappms.jobms.job.clients.ReviewClient;
import com.jobappms.jobms.job.dto.JobDTO;
import com.jobappms.jobms.job.external.Company;
import com.jobappms.jobms.job.external.Review;
import com.jobappms.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;
    int attempt = 0;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.
                mapToJobWithCompanyDto(job,company,reviews);
        //jobDTO.setCompany(company);

        return jobDTO;
    }

//    public List<String> companyBreakerFallback(Exception e){
//        List<String> list = new ArrayList<>();
//        list.add("Dummy");
//        return list;
//    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }


    // Modify get job by ID: want to add company object to response
    @Override
    public JobDTO getJobById(Long id){
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
