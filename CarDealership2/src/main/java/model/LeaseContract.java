package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class LeaseContract {
    private int leaseContractId;
    private String vin;
    private String customerFullName;
    private String customerPhone;
    private LocalDateTime leaseStartDate;
    private LocalDateTime leaseEndDate;
    private BigDecimal monthlyPayment;
    private int mileageLimit;
    private BigDecimal deposit;

    public LeaseContract() {
    }

    //Static end date
    public LeaseContract(int leaseContractId, String vin, String customerFullName, String customerPhone, LocalDateTime leaseStartDate, LocalDateTime leaseEndDate, BigDecimal monthlyPayment, int mileageLimit, BigDecimal deposit) {
        this.leaseContractId = leaseContractId;
        this.vin = vin;
        this.customerFullName = customerFullName;
        this.customerPhone = customerPhone;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.monthlyPayment = monthlyPayment;
        this.mileageLimit = mileageLimit;
        this.deposit = deposit;
    }

    public LeaseContract(int leaseContractId, String vin, String customerFullName, String customerPhone, LocalDateTime leaseStartDate, int durationMonths, BigDecimal monthlyPayment, int mileageLimit, BigDecimal deposit) {
        this.leaseContractId  = leaseContractId;
        this.vin              = vin;
        this.customerFullName = customerFullName;
        this.customerPhone    = customerPhone;
        this.leaseStartDate   = leaseStartDate;
        this.leaseEndDate     = leaseStartDate.plusMonths(durationMonths);
        this.monthlyPayment   = monthlyPayment;
        this.mileageLimit     = mileageLimit;
        this.deposit          = deposit;
    }

    public int getLeaseContractId() {
        return leaseContractId;
    }

    public void setLeaseContractId(int leaseContractId) {
        this.leaseContractId = leaseContractId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDateTime leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDateTime getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDateTime leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

}
