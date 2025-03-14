package Book_Management.demo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookInfoController {

    @GetMapping("/info")
    public BookInfo getBookInfo(@RequestParam String title, @RequestParam String author) {
        // 도서관 검색 결과 가져오기
        String libraryInfo = "도서관 A, 도서관 B"; // 실제로는 도서관 정보 API 호출

        return new BookInfo(title, author, libraryInfo);
    }

    static class BookInfo {
        private String title;
        private String author;
        private String libraryInfo;

        public BookInfo(String title, String author, String libraryInfo) {
            this.title = title;
            this.author = author;
            this.libraryInfo = libraryInfo;
        }

        // getters and setters
    }
}
