package com.cn.result.exception;

import com.cn.result.utils.GenericResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:29
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception ex) {
        if (BaseException.class.isAssignableFrom(ex.getClass())) {
            BaseException baseEx = (BaseException)ex;
            ErrorCode errorCode = (ErrorCode) Optional.ofNullable(baseEx.getErrorCode()).orElse(CommonErrorCode.SYSTEM_EXCEPTION);
            this.logException(errorCode, req.getRequestURI(), ex);
            GenericResult<Void> result = GenericResult.fail(errorCode.getCode(), new String[]{StringUtils.isBlank(baseEx.getMessage()) ? errorCode.getDescription() : baseEx.getMessage()});
            return new ResponseEntity(result, (MultiValueMap)null, HttpStatus.OK);
        } else {
            LOG.error("catch global exception with url:{}", req.getRequestURI(), ex);
            HttpStatus status = this.handleMvcExceptions(ex);
            return new ResponseEntity(GenericResult.fail(String.valueOf(status.value()), new String[]{"Service busy"}), status);
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(HttpServletRequest req, Exception ex) {
        HttpStatus status = this.handleMvcExceptions(ex);
        return new ResponseEntity(GenericResult.fail(String.valueOf(status.value()), new String[]{((ObjectError)((MethodArgumentNotValidException)ex).getBindingResult().getAllErrors().get(0)).getDefaultMessage()}), status);
    }

    private void logException(ErrorCode errorCode, String uri, Exception e) {
        if (errorCode.getErrorLevel() == null) {
            LOG.warn("catch global exception with url:{}", uri, e);
        } else {
            switch(errorCode.getErrorLevel()) {
                case ERROR:
                    LOG.error("catch global exception with url:{}", uri, e);
                    break;
                case INFO:
                    LOG.info("catch global exception with url:{}, msg: {}", uri, e.getMessage());
                    break;
                case WARN:
                    LOG.warn("catch global exception with url:{}, msg: {}", uri, e.getMessage());
            }
        }

    }

    private HttpStatus handleMvcExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            status = HttpStatus.NOT_ACCEPTABLE;
        } else if (ex instanceof MissingPathVariableException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MissingServletRequestParameterException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ServletRequestBindingException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ConversionNotSupportedException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof TypeMismatchException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotWritableException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestPartException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof BindException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof AsyncRequestTimeoutException) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        }

        return status;
    }
}
