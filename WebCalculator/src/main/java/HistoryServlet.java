import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("com.seavus.webCalculator/history.doGet");
		printMessage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		printMessage(req, resp);
	}

	@SuppressWarnings("unchecked")
	private void printMessage(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		ArrayList<Equation> equations = (ArrayList<Equation>) session.getAttribute("equations");

		if (equations == null) {
			equations = new ArrayList<Equation>();
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		for(Equation equation:equations)
			out.println(equation + "<br/>");
	}

}
