package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.ExpenseService.getExpenseCodeByProjectTypeAndName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL,"new project");
        // when
        ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,expenseType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project B");
        // when
        ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B,expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project other");
        // when
        ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"Project A");
        // then
        UnexpectedProjectTypeException unexpectedProjectTypeException = assertThrows(UnexpectedProjectTypeException.class,() -> {
                // when
                ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        });
        assertEquals("You enter invalid project type",unexpectedProjectTypeException.getMessage());
    }
}