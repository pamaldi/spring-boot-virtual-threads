package cloud.isaura.springbootvirtualthreads;

import java.time.LocalDateTime;

public class LaboratoryReport
{
    private LocalDateTime executionTime;

    private String externalId;

    private String fiscalCode;

    private String base64Image;

    public LocalDateTime getExecutionTime()
    {
        return executionTime;
    }

    public void setExecutionTime(LocalDateTime executionTime)
    {
        this.executionTime = executionTime;
    }

    public String getExternalId()
    {
        return externalId;
    }

    public void setExternalId(String externalId)
    {
        this.externalId = externalId;
    }

    public String getFiscalCode()
    {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode)
    {
        this.fiscalCode = fiscalCode;
    }

    public String getBase64Image()
    {
        return base64Image;
    }

    public void setBase64Image(String base64Image)
    {
        this.base64Image = base64Image;
    }

    @Override
    public String toString()
    {
        return "LaboratoryReport{" +
                "executionTime=" + executionTime +
                ", externalId='" + externalId + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }
}
