package Book_Management.demo.Controller;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.protobuf.ByteString;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("image") MultipartFile file) throws IOException {
        // 파일을 Vision API로 처리
        ByteString imgBytes = ByteString.copyFrom(file.getBytes());
        Image img = Image.newBuilder().setContent(imgBytes).build();

        // 텍스트 추출을 위한 기능 설정
        Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            List<AnnotateImageRequest> requests = List.of(request);
            client.batchAnnotateImages(requests).getResponsesList().forEach(response -> {
                TextAnnotation text = response.getFullTextAnnotation();
                String extractedText = text.getText(); // Vision API에서 추출된 텍스트

                // 추출된 텍스트에서 책 제목과 저자 파싱
                String title = extractTitle(extractedText);
                String author = extractAuthor(extractedText);

                System.out.println("Extracted Title: " + title);
                System.out.println("Extracted Author: " + author);

                // 이 정보를 데이터베이스나 다른 시스템에 전달할 수 있음
            });
        }
        return ResponseEntity.ok("File processed successfully.");
    }

    // 텍스트에서 책 제목을 추출하는 간단한 예시
    private String extractTitle(String text) {
        // 텍스트에서 책 제목을 찾는 로직 (예: 첫 번째 줄을 제목으로 간주)
        String[] lines = text.split("\n");
        return lines.length > 0 ? lines[0] : "Unknown Title";
    }

    // 텍스트에서 저자를 추출하는 간단한 예시
    private String extractAuthor(String text) {
        // 텍스트에서 저자 이름을 추출하는 로직 (예: 두 번째 줄을 저자명으로 간주)
        String[] lines = text.split("\n");
        return lines.length > 1 ? lines[1] : "Unknown Author";
    }
}
