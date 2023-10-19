package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class Controlador {

    @PostMapping("/register_identity")
    public Identity register_identity(@RequestBody Identity identity) throws SQLException, ClassNotFoundException {

        String document = identity.getDocument();
        String name = identity.getName();
        String cellphone = identity.getCellphone();
        String address = identity.getAddress();

        if (document == null || document.equals("") || document.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                cellphone == null || cellphone.equals("") || cellphone.length() < 0 || address == null || address.equals("") ||
                address.length() < 0) {

            return new Identity(null, null, null, null);
        } else {
            BD bd = new BD();
            identity = bd.register(document, name, cellphone, address);
        }
        return identity;
    }

    @PostMapping("/register_relations")
    public Relations register_relations(@RequestBody Relations relations) throws SQLException, ClassNotFoundException {

        String id = UUID.randomUUID().toString();
        id = id.replaceAll("-", "");
        id = id.substring(0, 6);
        String name = relations.getName();
        String ally = relations.getAlly();
        String document_identity = relations.getDocument_identity();


        if (name == null || name.equals("") || name.length() < 0 || ally == null || ally.equals("") || ally.length() < 0 ||
                document_identity == null || document_identity.equals("") || document_identity.length() < 0) {
            return new Relations(null, null, null, null);
        } else {
            BD bd = new BD();
            String document_Bd = BD.select_document(document_identity);

            if (document_Bd.equals("")) {
                return new Relations(null, null, null, Errors.error_document);
            } else {
                bd = new BD();
                relations = bd.register_relations(id, name, ally, document_identity);
            }
        }
        return relations;
    }

    @GetMapping("/search_identity")
    public List<Identity> search_identity() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Identity> list = bd.search_identity();

        return list;
    }

    @GetMapping("/search_relations")
    public List<Relations> search_relations() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Relations> list = bd.search_relations();

        return list;
    }

    @PostMapping("/edit_relations")
    public Relations edit_relations(@RequestBody Relations relations) throws SQLException, ClassNotFoundException {

        String id = relations.getId();
        String name = relations.getName();
        String ally = relations.getAlly();
        String document_identity = relations.getDocument_identity();


        if (id == null || id.equals("") || id.length() <0 || name == null || name.equals("") || name.length() < 0 || ally == null || ally.equals("") || ally.length() < 0 ||
                document_identity == null || document_identity.equals("") || document_identity.length() < 0) {
            return new Relations(null, null, null, null);
        } else {
            BD bd = new BD();
            String document_Bd = BD.select_document(document_identity);

            if (document_Bd.equals("")) {
                return new Relations(null, null, null, Errors.error_document);
            } else {
                bd = new BD();
                relations = bd.editRelation(id, name, ally, document_identity);
            }
        }
        return relations;
    }

    @PostMapping("/edit_identity")
    public Identity edit_identity(@RequestBody Identity identity) throws SQLException, ClassNotFoundException {

        String document = identity.getDocument();
        String name = identity.getName();
        String cellphone = identity.getCellphone();
        String address = identity.getAddress();

        if (document == null || document.equals("") || document.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                cellphone == null || cellphone.equals("") || cellphone.length() < 0 || address == null || address.equals("") ||
                address.length() < 0) {

            return new Identity(null, null, null, null);
        } else {
            BD bd = new BD();
            identity = bd.editIdentity(document, name, cellphone, address);
        }
        return identity;
    }


    @DeleteMapping("/delete_relation")
    public Relations delete_relation (@RequestBody Relations relations) throws SQLException, ClassNotFoundException {

        String id = relations.getId();
        if (relations.getId() == null || relations.getId().equals("") || relations.getId().length() < 0) {
            return new Relations(null, null, null, null);
        } else {

            int f = BD.DeleteRelation(id);
            if (f == 0) {
                return new Relations(Errors.error_deleteRelation, null, null, null);
            }
        }

        return relations;
    }

    @DeleteMapping("/delete_identity")
    public Identity delete_identity (@RequestBody Identity identity) throws SQLException, ClassNotFoundException {

        String id = identity.getDocument();
        if (identity.getDocument() == null || identity.getDocument().equals("") || identity.getDocument().length() < 0) {
            return new Identity(null, null, null, null);
        } else {

            int f = BD.DeleteIdentity(id);
            if (f == 0) {
                return new Identity(Errors.error_deleteIdentity, null, null, null);
            }
        }

        return identity;
    }
}

