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
		List<BoardDto> boardDtos = boardDao.boardList();
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
