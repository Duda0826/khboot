package com.spring.common.openapi;

import com.spring.openapi.data.dto.OpenApiDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class URLConnectUtil {
    public static StringBuilder openAPIData(OpenApiDTO api) throws Exception {
        HttpURLConnection conn = null;

        try{
            URI uri = new URI(api.getSiteName());
            URL url = uri.toURL();
            conn = (HttpURLConnection) url.openConnection();

            // 요청 방식 설정
            conn.setRequestMethod(api.getMethod());

            int responseCode = conn.getResponseCode();
            log.info("응답코드 : {}", responseCode);

            InputStream inputStream = responseCode >= 200 && responseCode < 300
                    ? conn.getInputStream() : conn.getErrorStream();

            try(BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String inputLine;
                StringBuilder output = new StringBuilder();
                while((inputLine = in.readLine()) != null) {
                    output.append(inputLine);
                }
                return output;
            }
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
    }
}
