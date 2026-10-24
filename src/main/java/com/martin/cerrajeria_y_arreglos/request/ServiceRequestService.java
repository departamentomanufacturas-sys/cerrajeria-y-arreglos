package com.martin.cerrajeria_y_arreglos.request;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequestService(ServiceRequestRepository repository) {
        this.repository = repository;
    }

    public List<ServiceRequest> findAll() {
        return repository.findAll();
    }

    public ServiceRequest findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service request not found"));
    }

    public ServiceRequest create(ServiceRequestInput input) {
        return repository.save(new ServiceRequest(
                input.customerName(), input.phone(), input.serviceType(), input.description()));
    }

    public ServiceRequest update(Long id, ServiceRequestInput input) {
        ServiceRequest request = findById(id);
        request.setCustomerName(input.customerName());
        request.setPhone(input.phone());
        request.setServiceType(input.serviceType());
        request.setDescription(input.description());
        return repository.save(request);
    }

    public void delete(Long id) {
        ServiceRequest request = findById(id);
        repository.delete(request);
    }
}
