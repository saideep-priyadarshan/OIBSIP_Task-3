import java.util.*;
public class ATM_Interface{
    String cardnum;
    int pin;
    String name;
    float balance;
    static String transaction="";
    static public int c=0;
    public	ArrayList<ATM_Interface> list = new ArrayList<>();

    ATM_Interface()
    {

    }

   ATM_Interface(String cardnum,int pin,String name,float balance)
    {
        this.cardnum=cardnum;
        this.pin=pin;
        this.name=name;
        this.balance=balance;
    }

    public String getcardnum()
    {
        return cardnum;
    }

    public int getpin()
    {
        return pin;
    }

    public String getname()
    {
        return name;
    }

    public float getbalance()
    {
        return balance;
    }

    public void setcardnum(String c1)
    {
        cardnum=c1;

    }

    public void setpin(int p1)
    {
        pin=p1;

    }

    public void setname(String n1)
    {
        name=n1;

    }

    public void setbalance(float b1)
    {
        balance=b1;

    }

    public void addValues(String num[], int pin[],
                          String name[], float balc[])
    {

        for (int i = 0; i <3; i++)
        {
            list.add(new ATM_Interface(num[i], pin[i],name[i],
                    balc[i]));
        }

    }
    public static void printoptions()
    {
        System.out.println("----------------****** MENU ******----------------");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Quit");
    }


    public static void transaction()
    {
        if(c>0)
        {
            System.out.println("Total number of Transactions: "+c+"");
            System.out.println("Transaction Details");
            System.out.println(transaction);
        }
        else
        {
            System.out.println("No Previous Transactions");
        }
    }


    public static void deposit(ATM_Interface obj)
    {
        System.out.println("How much would you like to deposit");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        float temp=amount+obj.getbalance();
        obj.setbalance(temp);
        System.out.println("Now your account balance is "+obj.getbalance());
        c++;
        transaction+="\n"+Float.toString(amount)+ " Rupees credited to your account"+"\n";
    }


    public static void withdraw(ATM_Interface obj)
    {
        System.out.println("How much would you like to withdraw");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        if(amount>obj.getbalance())
        {
            System.out.println("Insufficient balance");
        }
        else
        {

            while(amount%10!=0)
            {
                System.out.println("Enter amount in terms of 10's");
                amount = sc.nextFloat();
            }

            float temp=obj.getbalance()-amount;
            obj.setbalance(temp);
            System.out.println("Now your account balance is "+obj.getbalance());
            System.out.println("Dispensing Cash");
            c++;
            transaction+="\n"+Float.toString(amount)+" Rupees debited from your account"+"\n";
        }
    }

    public  static void transfer(ATM_Interface obj,String[] nums,float balc[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Account Number");
        String acctno=sc.nextLine();
        int i=0;
        int flag=0;


        for( i=0;i<nums.length;i++)
        {
            if(acctno.equals(nums[i]))
            {
                flag=1;
                break;
            }

        }


        if(flag==0)
        {
            System.out.println("Wrong Account Number");
            return;
        }

        System.out.println("Enter amount to transfer");
        float amount = sc.nextFloat();


        if(obj.balance >= amount)
        {
            c++;
            obj.balance -= amount;
            System.out.println("Transaction Successful!");
            System.out.println("Now your account balance is "+obj.getbalance());
            c++;
            balc[i]+=amount;
            transaction+="\n"+Float.toString(amount)+" Rupees transferred from your account to "+acctno+" \n";
        }
        else
        {
            System.out.println("Insufficient balance");
        }


    }


    public static void checkbalance(ATM_Interface obj)
    {
        System.out.println("Your Account balance is "+obj.getbalance());
    }


    public static void main(String args[])
    {
        String num[]={"8455096149846530","0934206979462259","6245433760920580"};

        int pin[]={9170,3315,6742};

        String name[]={"Saideep","Ojha","Sambit"};

        float balc[]={24800,62400,51800};

        ATM_Interface data=new ATM_Interface();

        data.addValues(num,pin,name,balc);

        System.out.println("Welcome to ATM");
        System.out.println("Please insert your card");
        String debitcardnum="";
        Scanner sc=new Scanner(System.in);
        ATM_Interface obj=null;
        int no_tries=0;

        while(true)
        {
            System.out.println("Enter Card Number");
            debitcardnum=sc.nextLine();

            for(int i=0;i<4;i++)
            {
                ATM_Interface c1=data.list.get(i);
                if(c1.getcardnum().equals(debitcardnum))
                {
                    obj=c1;
                    break;
                }
            }

            if(obj!=null||no_tries>3)
            {
                break;

            }

            else
            {
                no_tries=0;
                System.out.println("User not Registered... Try again");
            }
        }

        int userpin=0;
        System.out.println("Enter PIN");
        no_tries=0;

        while(true)
        {
            userpin=sc.nextInt();

            if(obj.getpin()==userpin || no_tries>3)
            {
                break;
            }
            else
            {
                no_tries++;
                System.out.println("Invalid PIN... Try again");
            }

        }

        System.out.println("Welcome "+obj.getname());
        System.out.println("Select an option to proceed");
        printoptions();
        int option=sc.nextInt();


        while(option<=5)
        {
            if(option==1)
            {
                transaction();
            }
            else if(option==2)
            {
                withdraw(obj);
            }
            else if(option==3)
            {
                deposit(obj);
            }
            else if(option==4)
            {
                transfer(obj,num,balc);
            }
            else if(option==5)
            {
                checkbalance(obj);
            }
            else
            {
                break;
            }

            printoptions();
            option=sc.nextInt();
        }
        System.out.println("Thank you!! Have a nice day");

    }
}