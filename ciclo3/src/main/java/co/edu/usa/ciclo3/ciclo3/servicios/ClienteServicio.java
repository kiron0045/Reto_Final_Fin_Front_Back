package co.edu.usa.ciclo3.ciclo3.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.ciclo3.ciclo3.entidades.Cliente;
import co.edu.usa.ciclo3.ciclo3.repositorios.ClienteRepository;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return  clienteRepository.getAll();
    }
    public Optional<Cliente> getCliente(int id){
        return clienteRepository.getCliente(id);
    }

    public Cliente save(Cliente c){
        if(c.getIdClient()==null){
            return clienteRepository.save(c);
        }else{
            Optional<Cliente> paux=clienteRepository.getCliente(c.getIdClient());
            if(paux.isEmpty()){
                return clienteRepository.save(c);
            }else{
                return c;
            }
        }
    }

    public Cliente update(Cliente c){
        if(c.getIdClient()!=null){
            Optional<Cliente> e= clienteRepository.getCliente(c.getIdClient());
            if(!e.isEmpty()){
                if(c.getName()!=null){
                    e.get().setName(c.getName());
                }
                if(c.getAge()!=null){
                    e.get().setAge(c.getAge());
                }
                if(c.getPassword()!=null){
                    e.get().setPassword(c.getPassword());
                }
                clienteRepository.save(e.get());
                return e.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    public boolean deleteClient(int cId) {
        Boolean aBoolean = getCliente(cId).map(c -> {
            clienteRepository.delete(c);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
