package via.pro3.Model;

public class Product
{
  private String productid;
  private String packagetype;
  private String animal;

  public Product(String productid, String packagetype, String animal)
  {
    this.productid = productid;
    this.packagetype = packagetype;
    this.animal = animal;
  }

  public String getProductid()
  {
    return productid;
  }

  public void setProductid(String productid)
  {
    this.productid = productid;
  }

  public String getPackagetype()
  {
    return packagetype;
  }

  public void setPackagetype(String packagetype)
  {
    this.packagetype = packagetype;
  }

  public String getAnimal()
  {
    return animal;
  }

  public void setAnimal(String animal)
  {
    this.animal = animal;
  }
}
