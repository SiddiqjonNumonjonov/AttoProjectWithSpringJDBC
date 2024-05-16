package dasturlash.uz.dtos;

import dasturlash.uz.enums.GeneralStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class CardDTO {
    private Integer id;
    private String cardNumber;
    private String expiredDate;
    private LocalDateTime createdAt;
    private Double balance;
    private Boolean visible;
    private GeneralStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiredDate=" + expiredDate +
                ", createdAt=" + createdAt +
                ", balance=" + balance +
                ", visible=" + visible +
                ", status=" + status +
                '}';
    }
}
