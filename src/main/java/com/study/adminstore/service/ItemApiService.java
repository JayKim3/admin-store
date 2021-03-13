package com.study.adminstore.service;

import com.study.adminstore.model.entity.Files;
import com.study.adminstore.model.entity.Item;
import com.study.adminstore.model.network.request.ItemApiRequest;
import com.study.adminstore.model.network.response.ItemApiResponse;
import com.study.adminstore.repository.FilesRepository;
import com.study.adminstore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemApiService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FilesRepository filesRepository;

    public ResponseEntity<ItemApiResponse> create(MultipartFile files, ItemApiRequest itemApiRequest) throws IOException {
        Item item = Item.builder()
                .content(itemApiRequest.getContent())
                .name(itemApiRequest.getName())
                .price(itemApiRequest.getPrice())
                .status("T")
                .createdAt(LocalDateTime.now())
                .createdBy("KSJ")
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .updatedBy("KSJ")
                .build();

        itemSave(files, item);
        itemRepository.save(item);

        return new ResponseEntity<>(response(item), HttpStatus.OK);

    }

    public void itemSave(MultipartFile requestFile, Item item) throws IOException {
        String sourceFileName = requestFile.getOriginalFilename();
        final String fileUrl = "/Users/sjk/IdeaProjects/admin-store/src/main/resources/static/images/item";
        String destinationFileName;

        destinationFileName = sourceFileName;
        File destinationFile = new File(fileUrl + destinationFileName);

        destinationFile.getParentFile().mkdirs();
        requestFile.transferTo(destinationFile); // 업로드할 파일 destinationFile로 지정

        Files imageFiles = new Files();
        imageFiles.setFilename(destinationFileName);
        imageFiles.setFileOriName(sourceFileName);
        imageFiles.setFileurl(fileUrl);
        imageFiles.setItem(item);
        filesRepository.save(imageFiles);
    }

    public List<Item> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by(Sort.Direction.ASC, "id"));
        Page<Item> all = itemRepository.findAll(pageable);
        List<Item> items = all.getContent();
        return items;
    }

    private ItemApiResponse response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .content(item.getContent())
                .name(item.getName())
                .price(item.getPrice())
                .build();

        return itemApiResponse;
    }
}
