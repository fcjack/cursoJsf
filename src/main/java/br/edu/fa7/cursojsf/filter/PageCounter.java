package br.edu.fa7.cursojsf.filter;

import br.edu.fa7.cursojsf.service.UrlAcessoService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("*.xhtml")
public class PageCounter implements Filter {
    @Inject
    private UrlAcessoService urlAcessoService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        if (url.endsWith(".xhtml") && !ignore(url)) {
            urlAcessoService.incrementCountUrl(url);
        }

        chain.doFilter(request, response);
    }

    private boolean ignore(String url) {
        return url.contains("javax.faces.resource");
    }

    @Override
    public void destroy() {

    }
}
