package br.edu.fa7.cursojsf.listener;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HttpListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) context.getRequest();
        HttpServletResponse res = (HttpServletResponse) context.getResponse();

        String uri = req.getRequestURI();

        HttpSession session = req.getSession(false);
        if (!ignore(uri) && !uri.contains("login.xhtml") && (session == null || (session.getAttribute("userLogged") == null))) {
            context.log("acesso nao autorizado: " + uri);
            try {
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private boolean ignore(String url) {
        return url.contains("javax.faces.resource");
    }
}
