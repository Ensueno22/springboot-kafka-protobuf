package io.ensueno.sender.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * 발송 결과 정보 반환
 *
 * @author pioneer
 * @since 0.1
 */
@Data
@Builder
@ToString
public class ResEmMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key                   ;  //발송 요청한 Key 정보 Message.key
    private String reqSendTime    ;  //발송 요청한 시간
    private String sentTime       ;  //발송 시간
    private String resultTime     ;  //발송 결과 수신 시간
    private String code                  ;  // 발송 결과 정보
    private String transCode             ;  // 연동 에러 코드
    private boolean retry                ;
    private Map<Object, Object> userInfo ;  //추가 정보
}
