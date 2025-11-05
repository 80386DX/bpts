package rs.digit.bpts.mapper;
import org.springframework.beans.BeanUtils;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.dto.TransferRequestDTO;


public class Converter {

    public Account convertToEntity (TransferRequestDTO dto){

        Account account = new Account();

        if(dto != null){
            BeanUtils.copyProperties(dto, account);
        }

        return account;
    }



}
