package com.zzh.oss.Service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.zzh.commonutils.ResultCodeEnum;
import com.zzh.oss.Service.OssFileService;
import com.zzh.oss.utils.ConstantPropertiesUtils;
import com.zzh.servicebase.exceptionHandler.GuliException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssFileServiceImpl implements OssFileService {


    @Override
    public String uploadFile(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtils.FILE_HOST;

        String uploadUrl = null;

        try {
            //创建oss实例
            OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
            //文件流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2020/10/21/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR.getCode(),ResultCodeEnum.FILE_UPLOAD_ERROR.getMessage());
        }
        return uploadUrl;
    }
}
