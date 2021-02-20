package nl.novi.eindopdracht.exceptions;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username){super("Cannot find user.");}
}
