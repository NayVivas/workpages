package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemFiles;
import com.hipergarzon.workpages.models.ItemImage;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemFilesDTO {
    private Long id;
    @Setter
    private String fileName;
    @Setter
    private String fileType;
    @Setter
    private String url;

    private Long size;

    private byte[] content;

    public ItemFilesDTO() {
    }

    public ItemFilesDTO(ItemFiles itemImage) {
        this.id = itemImage.getId();
        this.fileName = itemImage.getFileName();
        this.fileType = itemImage.getFileType();
        this.url = itemImage.getUrl();
        this.content = itemImage.getContent();
    }

    public ItemFilesDTO(String fileName, String fileType, String url, Long size, Long id, byte[] content) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.size = size;
        this.fileType = fileType;
        this.content = content;
    }

    public ItemFilesDTO(ItemImage itemImage) {
        this.id = itemImage.getId();
        this.fileName = itemImage.getFileName();
        this.fileType = itemImage.getFileType();
        this.url = itemImage.getUrl();
        this.content = itemImage.getContent();
    }
}
