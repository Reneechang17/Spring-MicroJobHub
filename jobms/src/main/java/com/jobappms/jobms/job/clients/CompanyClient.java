package com.jobappms.jobms.job.clients;

import com.jobappms.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE", url = "${company-service.url}")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id")  Long id);
}
