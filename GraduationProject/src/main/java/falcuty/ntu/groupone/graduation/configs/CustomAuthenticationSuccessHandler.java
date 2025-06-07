package falcuty.ntu.groupone.graduation.configs;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Lấy danh sách quyền của người dùng
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Kiểm tra role và chuyển hướng phù hợp
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_SUPERVISOR")) {
                response.sendRedirect("/supervisors/home");
                return;
            } else if (role.equals("ROLE_STUDENT")) {
                response.sendRedirect("/students/home");
                return;
            } else if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/home");
                return;
            }
        }

        // Nếu không có role phù hợp → chuyển về trang mặc định
        response.sendRedirect("/");
    }
}
