package nmtt.demo.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import nmtt.demo.dto.request.Account.ApiResponse;
import nmtt.demo.service.BorrowingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrowing")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowingController {
    BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ApiResponse<String> borrowBook(@RequestParam String accountId, @RequestParam String bookId) {
        try {
            borrowingService.borrowBook(accountId, bookId);
            return ApiResponse.<String>builder()
                    .result("Book borrowed successfully!")
                    .build();
        } catch (IllegalArgumentException e) {
            return ApiResponse.<String>builder()
                    .result(e.getMessage())
                    .build();
        }
    }

    @PostMapping("/return")
    public ApiResponse<String> returnBook(@RequestParam String accountId, @RequestParam String bookId) {
        try {
            borrowingService.returnBook(accountId, bookId);
            return ApiResponse.<String>builder()
                    .result("Book returned successfully!")
                    .build();
        } catch (IllegalArgumentException e) {
            return ApiResponse.<String>builder()
                    .result(e.getMessage())
                    .build();
        }
    }
}
