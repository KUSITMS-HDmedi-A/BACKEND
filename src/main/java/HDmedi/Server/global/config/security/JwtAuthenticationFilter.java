package HDmedi.Server.global.config.security;



import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) {
    try{
        String token = jwtTokenProvider.resolveAccessToken(servletRequest);
        if (token != null) {

            jwtTokenProvider.validateToken(token);

            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            setErrorResponse(servletRequest, servletResponse, "검증 에러 ",421);
        } catch (ExpiredJwtException e) {
            setErrorResponse(servletRequest, servletResponse, "토큰 만료",419);
        } catch (JwtException e) {
            setErrorResponse(servletRequest, servletResponse, "jwt 에러",420);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, String message,
                                 int code) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final Map<String, Object> body = new HashMap<>();

        body.put("message", message);
        body.put("code", code);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);
    }


    public void errorResponse(HttpServletRequest servletRequest,
                              HttpServletResponse servletResponse,
                              String message,
                              int code
                         ) throws IOException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json; charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", code);
        errorResponse.put("message", message);

        // JSON으로 변환하여 응답 보내기
        PrintWriter out = httpServletResponse.getWriter();
        objectMapper.writeValue(out, errorResponse);
        out.flush();
    }

}

