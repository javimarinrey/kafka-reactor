package org.example.productor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    private String id;
    private String tipo;
    private String payload;
    private long timestamp;
}
