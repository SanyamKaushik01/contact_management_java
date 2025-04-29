package service;

import entity.Contact;
import repository.ContactRepository;
import java.util.List;

public class ContactServiceImpl implements ContactService {
    private ContactRepository repository = new ContactRepository();

    @Override
    public void addContact(Contact contact) {
        repository.addContact(contact);
    }
    @Override
    public List<Contact> searchByField(String field, String value) {
        return repository.findByField(field, value);
    }
    @Override
    public boolean isPhoneTaken(String phone, String excludeName) {
        return repository.phoneExists(phone, excludeName);
    }

    @Override
    public List<Contact> viewContacts() {
        return repository.getAllContacts();
    }

    @Override
    public void searchContact(String name) {
        repository.findByName(name);
    }

    @Override
    public boolean deleteContact(String name) {
        return repository.deleteContact(name);
    }

    @Override
    public boolean updateContact(String name, Contact updatedContact) {
        Contact existingContact = repository.findByName(name);
        if (existingContact != null) {
            existingContact.setName(updatedContact.getName());
            existingContact.setPhone(updatedContact.getPhone());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setAddress(updatedContact.getAddress());
            return true;
        }
        return false;
    }
}
