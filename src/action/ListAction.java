package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;

public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		BoardDao dbPro = new BoardDao();
		int pageSize = 5 ; //페이징 처리를 위함, 한 페이지에 보여지는 글 수
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		
		//만약 페이지가 1이면(currentPage) rnum이 1에서 5까지인 글을 보여줘야하므로 start가 1이 되어야 될테고 
		//end는 5가 되야함 페이지가 2면 rnum이 6에서 10까지인 글을 보여줘야하므로 start는 6이 되어야될테고
		//end는 10이 되야함. 밑에 식이 그런식이다. 
		int start = (currentPage-1) * pageSize + 1; 
		int end = currentPage * pageSize;		
		
		List<BoardDto> articleList = dbPro.getArticles(start,end); 
		int count = dbPro.getArticleCount();
		
		req.setAttribute("page", count/pageSize+1); // 만약 글이 6개면 5개씩 페이징처리므로 페이지가 2개고 11개면 3개고 그런 느
		req.setAttribute("writecount", count );
		req.setAttribute("articleList", articleList);
		return "/WEB-INF/library/list.jsp";
	}

}
