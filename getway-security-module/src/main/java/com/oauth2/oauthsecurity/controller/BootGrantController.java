package com.oauth2.oauthsecurity.controller;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
// 必须配置
@SessionAttributes("authorizationRequest")
public class BootGrantController {

    private static String CSRF = "<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />";
    private static String DENIAL = "<form id='denialForm' name='denialForm' action='${path}/oauth/authorize' method='post'><input name='user_oauth_approval' value='false' type='hidden'/>%csrf%<label><input name='deny' value='Deny' type='submit'/></label></form>";
    private static String TEMPLATE = "<html>" +
            "<body>" +
            "<div style='display:none;'>" +  //增加DIV 把HTML内容隐藏
            "<h1>OAuth Approval</h1><p>Do you authorize '${authorizationRequest.clientId}' to access your protected resources?</p><form id='confirmationForm' name='confirmationForm' action='${path}/oauth/authorize' method='post'><input name='user_oauth_approval' value='true' type='hidden'/>%csrf%%scopes%<label><input name='authorize' value='Authorize' type='submit'/></label></form>%denial%" +
            "</div>" +
            "<script>document.getElementById('confirmationForm').submit()</script>" + //白页面--自动提交表单
            "</body>" +
            "</html>";
    private static String SCOPE = "<li><div class='form-group'>%scope%: <input type='radio' name='%key%' value='true'%approved%>Approve</input> <input type='radio' name='%key%' value='false'%denied%>Deny</input></div></li>";

    public BootGrantController() {
    }

    @RequestMapping({"/oauth/confirm_access"})
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String template = this.createTemplate(model, request);
        if (request.getAttribute("_csrf") != null) {
            model.put("_csrf", request.getAttribute("_csrf"));
        }

        return new ModelAndView(new SsoSpelView(template), model);
    }

    protected String createTemplate(Map<String, Object> model, HttpServletRequest request) {
        String template = TEMPLATE;
        if (!model.containsKey("scopes") && request.getAttribute("scopes") == null) {
            template = template.replace("%scopes%", "").replace("%denial%", DENIAL);
        } else {
            template = template.replace("%scopes%", this.createScopes(model, request)).replace("%denial%", "");
        }

        if (!model.containsKey("_csrf") && request.getAttribute("_csrf") == null) {
            template = template.replace("%csrf%", "");
        } else {
            template = template.replace("%csrf%", CSRF);
        }

        return template;
    }

    private CharSequence createScopes(Map<String, Object> model, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder("<ul>");
        Map<String, String> scopes = (Map)((Map)(model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes")));
        for (String scope : scopes.keySet()) {
            String approved = "true".equals(scopes.get(scope)) ? " checked" : "";
            String denied = !"true".equals(scopes.get(scope)) ? " checked" : "";
            String value = SCOPE.replace("%scope%", scope).replace("%key%", scope).replace("%approved%", approved).replace("%denied%", denied);
            builder.append(value);
        }

        builder.append("</ul>");
        return builder.toString();
    }

    private static class SsoSpelView implements View{
        private final String template;
        private final String prefix;
        private final SpelExpressionParser parser = new SpelExpressionParser();
        private final StandardEvaluationContext context = new StandardEvaluationContext();
        private PropertyPlaceholderHelper.PlaceholderResolver resolver;

        public SsoSpelView(String template) {
            this.template = template;
            this.prefix = (new RandomValueStringGenerator()).generate() + "{";
            this.context.addPropertyAccessor(new MapAccessor());
            this.resolver = name -> {
                Expression expression = SsoSpelView.this.parser.parseExpression(name);
                Object value = expression.getValue(SsoSpelView.this.context);
                return value == null ? null : value.toString();
            };
        }

        public String getContentType() {
            return "text/html";
        }

        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            Map<String, Object> map = new HashMap<>(model);
            String path = ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
            map.put("path", path == null ? "" : path);
            this.context.setRootObject(map);
            String maskedTemplate = this.template.replace("${", this.prefix);
            PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(this.prefix, "}");
            String result = helper.replacePlaceholders(maskedTemplate, this.resolver);
            result = result.replace(this.prefix, "${");
            response.setContentType(this.getContentType());
            response.getWriter().append(result);
        }

    }
}
