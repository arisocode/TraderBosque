package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

    @JsonProperty("document_type")
    private String documentType;

    @JsonProperty("document_sub_type")
    private String documentSubType;

    @JsonProperty("content")
    private String content; // Base64 string

    @JsonProperty("mime_type")
    private String mimeType;
}