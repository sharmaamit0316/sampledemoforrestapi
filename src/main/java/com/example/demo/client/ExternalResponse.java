package com.example.demo.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalResponse {

    private String status;
    private String message;
    private DataSet data;  // Now using the specific `Data` type for the `data` field

}

