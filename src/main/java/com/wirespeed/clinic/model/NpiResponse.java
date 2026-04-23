package com.wirespeed.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Maps the Federal CMS NPPES API response to Java.
 * @JsonIgnoreProperties(ignoreUnknown = true) is critical because the 
 * registry returns dozens of fields (addresses, etc.) we don't need.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NpiResponse {

    @JsonProperty("result_count")
    private int resultCount;

    private List<Object> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
}