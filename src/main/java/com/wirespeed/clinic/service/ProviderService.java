package com.wirespeed.clinic.service;

import com.wirespeed.clinic.model.Provider;
import com.wirespeed.clinic.repository.AppointmentRepository;
import com.wirespeed.clinic.repository.ProviderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final AppointmentRepository appointmentRepository;

    public ProviderService(ProviderRepository providerRepository, AppointmentRepository appointmentRepository) {
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(Integer id) {
        return findProviderById(id);
    }

    public Provider createProvider(Provider provider) {
        validateForSave(provider, null);
        return providerRepository.save(provider);
    }

    public Provider updateProvider(Integer id, Provider provider) {
        Provider existingProvider = findProviderById(id);
        validateForSave(provider, id);

        existingProvider.setFirstName(provider.getFirstName());
        existingProvider.setLastName(provider.getLastName());
        existingProvider.setNpiNumber(provider.getNpiNumber());
        existingProvider.setSpecialty(provider.getSpecialty());

        return providerRepository.save(existingProvider);
    }

    public void deleteProvider(Integer id) {
        Provider provider = findProviderById(id);
        if (appointmentRepository.existsByProviderId(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete provider with existing appointments");
        }
        providerRepository.delete(provider);
    }

    public Provider findProviderById(Integer id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Provider with id " + id + " not found"));
    }

    @SuppressWarnings("deprecation")
    private void validateForSave(Provider provider, Integer currentProviderId) {
        if (provider == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provider body is required");
        }
        if (provider.getFirstName() == null || provider.getFirstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if (provider.getLastName() == null || provider.getLastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }
        if (provider.getNpiNumber() == null || provider.getNpiNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NPI number is required");
        }
        if (!provider.getNpiNumber().matches("\\d{10}")) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "NPI number must be exactly 10 digits");
        }
        Provider existingProvider = providerRepository.findByNpiNumber(provider.getNpiNumber()).orElse(null);
        if (existingProvider != null && !existingProvider.getId().equals(currentProviderId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Provider with NPI '" + provider.getNpiNumber() + "' already exists");
        }
    }
}
