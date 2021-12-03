package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		BoardDto article = new BoardDto();
		BoardDao dbPro = new BoardDao();

		//특정 num의 article을 조회하기 위함
		int num = Integer.parseInt(req.getParameter("num"));
		article = dbPro.getArticle(num);
		
		
		//article을 속성으로 가져감
		req.setAttribute("article",article);
		
		return "/WEB-INF/library/content.jsp";
	}

}
