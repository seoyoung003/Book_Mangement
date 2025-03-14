package Book_Management.demo.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class LibrarySearchController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/book")
    public String getLibraryInfo(@RequestParam String title, @RequestParam String author) {
        // 공공데이터 API 호출 예시 (실제 API URL과 파라미터에 맞게 조정)
        String apiUrl = "http://data4library.kr/api/bookExist?authKey="[발급받은키]"&libCode="[도서관코드]"&isbn13="[ISBN];


        // 공공 API 호출
        String response = restTemplate.getForObject(apiUrl, String.class);

        // 도서관 정보 처리
        return response; // 도서관 정보 반환
    }
}
