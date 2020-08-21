package com.mym.consulting.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAdvanceResponse extends Response {
    private List<String> labelList;

    private List<Double> dataList;

    public ProjectAdvanceResponse(String message, List<String> labelList, List<Integer> dataList) {
        super.setResponseMessage(message);
        this.labelList = labelList;
    }
}
