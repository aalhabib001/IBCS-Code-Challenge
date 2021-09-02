package bd.info.habib.ibcscodechallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicApiResponse<T> {
    int statusCode;
    String message;

    T data;
}
