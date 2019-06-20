package pia.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns =
{
    "*.xhtml"
})
public class AuthorizationFilter implements Filter
{

    List<String> allowedForRegularUser = new ArrayList<>();

    public AuthorizationFilter()
    {
        allowedForRegularUser.add("change_password.xhtml");
        allowedForRegularUser.add("index.xhtml");
        allowedForRegularUser.add("login.xhtml");
        allowedForRegularUser.add("navigator.xhtml");
        allowedForRegularUser.add("register.xhtml");
        allowedForRegularUser.add("template.xhtml");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        try
        {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = req.getSession(true);
            String urlToLogin = req.getContextPath() + "/login.xhtml";
            String reqURI = req.getRequestURI();

            boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);
            if (resourceRequest)
            {
                chain.doFilter(req, resp);
                return;
            }

            if (ses.getAttribute("korisnickoIme") == null || ses.getAttribute("tip") == null)
            {
                if (reqURI.contains("/igra/") || reqURI.contains("/admin/") || reqURI.contains("/supervisor/"))
                {
                    resp.sendRedirect(req.getContextPath() + "/login.xhtml");
                }
                else
                {
                    chain.doFilter(request, response);
                }
            }
            else
            {
                int access = (int) ses.getAttribute("tip");

                if (access == 0)
                {
                    if (reqURI.contains("/admin/") || reqURI.contains("/supervisor/"))
                    {
                        resp.sendRedirect(req.getContextPath() + "/igra/meni.xhtml");
                    }
                    else
                    {
                        chain.doFilter(request, response);
                    }
                }
                else if (access == 1)
                {
                    if (reqURI.contains("/supervisor/"))
                    {
                        chain.doFilter(request, response);
                    }
                    else
                    {
                        resp.sendRedirect(req.getContextPath() + "/supervisor/supervisor.xhtml");
                    }
                }
                else if (access == 2)
                {
                    if (reqURI.contains("/admin/"))
                    {
                        chain.doFilter(request, response);
                    }
                    else
                    {
                        resp.sendRedirect(req.getContextPath() + "/admin/administrator.xhtml");
                    }
                }
                else
                {

                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy()
    {

    }
}