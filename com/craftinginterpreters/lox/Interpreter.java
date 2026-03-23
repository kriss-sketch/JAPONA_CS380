package com.craftinginterpreters.lox;

import java.util.List;

class Interpreter implements Expr.Visitor<Object>,
                             Stmt.Visitor<Void> {

	private Object evaluate(Expr expr) {
		return expr.accept(this);
	}

	void interpret(List<Stmt> statements) {
		try {
			for (Stmt statement : statements) {
				execute(statement);
			}
		} catch (RuntimeError error) {
			Lox.runtimeError(error);
		}
  	}

	private String stringify(Object object) {
		if (object == null) return "nil";

		if (object instanceof Double) {
			String text = object.toString();
			if (text.endsWith(".0")) {
				text = text.substring(0, text.length() - 2);
			}
			return text;
		}
		return object.toString();
	}

	private void execute(Stmt stmt) {
		stmt.accept(this);
	}

	@Override
	 public Void visitExpressionStmt(Stmt.Expression stmt) {
		evaluate(stmt.expression);
		return null;
	}

  	@Override
	 public Void visitPrintStmt(Stmt.Print stmt) {
		Object value = evaluate(stmt.expression);
		System.out.println(stringify(value));
		return null;
	}

	@Override
	public Object visitLiteralExpr(Expr.Literal expr) {
		return expr.value;
	}
	@Override
	public Object visitGroupingExpr(Expr.Grouping expr) {
		return evaluate(expr.expression);
	}

	@Override
	public Object visitUnaryExpr(Expr.Unary expr) {
		Object right = evaluate(expr.right);

		switch (expr.operator.type) {
			case MINUS:
				checkNumberOperand(expr.operator, right);
				return -(double)right;
			case BANG:
				return !isTruthy(right);
			default:
				return null;
		}
	}

	private void checkNumberOperand(Token operator, Object operand) {
		if (operand instanceof Double) return;
		throw new RuntimeError(operator, "Operand must be number");
	}

	private boolean isTruthy(Object object) {
		if (object == null) return false;
		if (object instanceof Boolean) return (boolean)object;
		return true;
	}

	@Override
	public Object visitBinaryExpr(Expr.Binary expr) {
		Object left = evaluate(expr.left);
		Object right = evaluate(expr.right);

		switch (expr.operator.type) {
			case BANG_EQUAL:
				return !isEqual(left, right);
			case EQUAL_EQUAL:
				return isEqual(left, right);
			case GREATER:
				checkNumberOperands(expr.operator, left, right);
				return (double)left > (double)right;
			case GREATER_EQUAL:
				checkNumberOperands(expr.operator, left, right);
				return (double)left >= (double)right;
			case LESS:
				checkNumberOperands(expr.operator, left, right);
				return (double)left < (double)right;
			case LESS_EQUAL:
				checkNumberOperands(expr.operator, left, right);
				return (double)left <= (double)right;
			case MINUS:
				checkNumberOperands(expr.operator, left, right);
				return (double)left - (double)right;
			case SLASH:
				checkNumberOperands(expr.operator, left, right);
				return (double)left / (double)right;
			case STAR:
				checkNumberOperands(expr.operator, left, right);
				return (double)left * (double)right;
			case PLUS:
				if (left instanceof Double && right instanceof Double) {
					return (double)left + (double)right;
				}
				if (left instanceof String && right instanceof String) {
					return (String)left + (String)right;
				}
				throw new RuntimeError(expr.operator, "RAH");
			default:
				return null;
		}
	}


	private void checkNumberOperands(Token operator, Object left, Object right) {
		if (left instanceof Double && right instanceof Double) return;
		throw new RuntimeError(operator, "number please");
	}

	private boolean isEqual(Object a, Object b) {
		if (a == null && b == null) return true;
		if (a == null) return false;

		return a.equals(b);
	}

	@Override
	public Object visitVariableExpr(Expr.Variable expr) {
		return null;
	}

	@Override
	public Object visitAssignExpr(Expr.Assign expr) {
		return null;
	}

	@Override
	public Object visitLogicalExpr(Expr.Logical expr) {
		return null;
	}

	@Override
	public Object visitCallExpr(Expr.Call expr) {
		return null;
	}

	@Override
	public Object visitGetExpr(Expr.Get expr) {
		return null;
	}

	@Override
	public Object visitSetExpr(Expr.Set expr) {
		return null;
	}

	@Override
	public Object visitThisExpr(Expr.This expr) {
		return null;
	}

	@Override
	public Object visitSuperExpr(Expr.Super expr) {
		return null;
	}

	@Override
	public Void visitBlockStmt(Stmt.Block stmt) {
		return null;
	}

	@Override
	public Void visitClassStmt(Stmt.Class stmt) {
		return null;
	}

	@Override
	public Void visitFunctionStmt(Stmt.Function stmt) {
		return null;
	}

	@Override
	public Void visitIfStmt(Stmt.If stmt) {
		return null;
	}

	@Override
	public Void visitReturnStmt(Stmt.Return stmt) {
		return null;
	}

	@Override
	public Void visitVarStmt(Stmt.Var stmt) {
		return null;
	}

	@Override
	public Void visitWhileStmt(Stmt.While stmt) {
		return null;
	}
}