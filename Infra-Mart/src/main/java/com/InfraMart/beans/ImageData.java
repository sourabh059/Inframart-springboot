package com.InfraMart.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





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
