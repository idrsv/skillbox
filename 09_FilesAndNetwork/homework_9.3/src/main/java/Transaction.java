import java.util.Objects;

public class Transaction {
    public double income;
    public double expense;
    public String description;

    public Transaction(double income, double expense, String description) {
        this.income = income;
        this.expense = expense;
        this.description = description;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.income, income) == 0 && Double.compare(that.expense, expense) == 0 && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(income, expense, description);
    }
}
