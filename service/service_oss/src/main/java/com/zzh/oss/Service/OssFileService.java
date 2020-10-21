package com.zzh.oss.Service;

import org.springframework.web.multipart.MultipartFile;

public interface OssFileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
