
package com.target.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "process_status"
})
public class BusinessProcessStatu {

    @JsonProperty("process_status")
    private ProcessStatus processStatus;

    /**
     * 
     * @return
     *     The processStatus
     */
    @JsonProperty("process_status")
    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    /**
     * 
     * @param processStatus
     *     The process_status
     */
    @JsonProperty("process_status")
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

}
