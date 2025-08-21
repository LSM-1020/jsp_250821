package com.LSM.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Boardcontroller
 */
@WebServlet("/boardlist") //boardlist요청만 구현
public class Boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardDao boardDao = new BoardDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Boardcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		//게시판링크 접근할때 처음 페이지는 무조건 1페이지의 내용이 보여야 함
		//처음에 보여질 페이지의 번호의 초기값을 1로 초기화
		if(request.getParameter("page") == null) {//참이면 링크를 타고 게시판으로 들어간 경우
			page = 1;
		} else { //유저가 보고싶은 페이지번호를 누른 경우
			page = Integer.parseInt( request.getParameter("page")); //유저가 클릭한 유저가 보고싶어하는 페이지의 번호
		}
		
		
	
		
		List<BoardDto> boardDtos = boardDao.boardList(page);
		request.setAttribute("boardDtos", boardDtos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");	
		
		//conPath자리에는 실제 실행시킬 jsp파일 이름, boardList.jsp에게 request객체를 전달해라 그후 boardList.jsp로 이동해라
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
