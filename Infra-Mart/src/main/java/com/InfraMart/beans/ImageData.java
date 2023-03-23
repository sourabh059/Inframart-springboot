package com.InfraMart.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

import javax.persistence.*;





@Entity
@Table(name ="ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgid;

    private String name;
    
    private String type;
    
    
    @Lob
    @Column(name = "imagedata", length = Integer.MAX_VALUE, nullable = true)
    private byte[] imageData;
  
	

//    @OneToOne(mappedBy ="imgid")
//    private Product p;
    
}
