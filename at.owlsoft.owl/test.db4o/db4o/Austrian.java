package db4o;

import java.util.List;

public class Austrian extends Human
{
    private int _insuranceNumber;

    public Austrian(List<Pet> slaves, String name, int insuranceNumber)
    {
        super(slaves, name);
        setInsuranceNumber(insuranceNumber);
        // TODO Auto-generated constructor stub
    }

    public int getInsuranceNumber()
    {
        return _insuranceNumber;
    }

    public void setInsuranceNumber(int insuranceNumber)
    {
        _insuranceNumber = insuranceNumber;
    }

}
