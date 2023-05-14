class Employee {

    // write fields
    protected String name;
    protected String email;
    protected int experience;

    public Employee(String n, String e, int ex){
        name = n;
        email = e;
        experience = ex;
    }

    // write constructor

    public void setName(String n) {
        name = n;
    }

    public void setEmail(String e){
        email = e;
    }
    
    public void setExperience(int e){
        experience = e;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    // write getters
}

class Developer extends Employee {


    protected String mainLanguage;
    protected String[] skills;

    public Developer(String n, String e, int ex, String ml, String[] skill){
        super(n, e, ex);
        mainLanguage = ml;
        skills = skill;
    }

    public void setMainLanguage(String e){
        mainLanguage = e;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }    
    
    public void setSkills(String[] sk){
        skills = sk;
    }

    public String[] getSkills() {
        return skills;
    }

    // write getters
}

class DataAnalyst extends Employee {

    // write fields
    protected boolean phd;
    protected String[] methods;

    // write constructor
    public DataAnalyst(String n, String e, int ex, boolean p, String[] m){
        super(n, e, ex);
        phd = p;
        methods = m;
    }
    
    public void setPhd(boolean e){
        phd = e;
    }

    public boolean isPhd() {
        return phd;
    }    
    
    public void setMethods(String[] sk){
        methods = sk;
    }

    public String[] getMethods() {
        return methods;
    }

    // write getters
}
