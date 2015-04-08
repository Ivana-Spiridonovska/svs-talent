import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("com.seavus.webCalculator/calculate.doGet");
		printMessage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("com.seavus.webCalculator/calculate.doPost");
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
		try {
			int operand1 = Integer.parseInt(req.getParameter("operandOne"));
			int operand2 = Integer.parseInt(req.getParameter("operandTwo"));
			Operator operator = Operator.valueOf(req.getParameter("operator"));
			Equation equation = new Equation(operand1, operand2, operator);
			int result = calculate(operand1, operand2, operator);
			equation.setResult(result);
			out.println(equation.toString());
			
			equations.add(equation);
			session.setAttribute("equations", equations);

		} catch (IllegalArgumentException e) {
			out.println("Invalid argument");
		}

	}

	public int calculate(int op1, int op2, Operator operator) {
		int result = 0;
		switch (operator) {
		case ADD:
			result = op1 + op2;
			break;
		case SUBTRACT:
			result = op1 - op2;
			break;
		}
		return result;
	}

}
