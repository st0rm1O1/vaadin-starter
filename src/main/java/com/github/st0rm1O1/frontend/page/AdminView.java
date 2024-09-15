package com.github.st0rm1O1.frontend.page;

import com.github.st0rm1O1.backend.entity.Book;
import com.github.st0rm1O1.backend.service.BookService;
import com.github.st0rm1O1.common.ImplProvider;

import com.github.st0rm1O1.frontend.page.fallback.NotFoundView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.*;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoIcon;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;


@Route("admin")
//@RolesAllowed("ADMIN")
@AnonymousAllowed
public class AdminView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String> {

    private String title = "";

    @Override
    public String getPageTitle() {
        return title;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            title = "Admin Panel #" + parameter;
        } else {
//            title = "Blog Home";
            event.rerouteTo(NotFoundView.class);
        }
    }




    private final BookService service;
    private static List<Book> allBooks;
    private static Grid<Book> bookGrid;

    public AdminView(BookService service) {
        this.service = service;
        createGrid();
        setSizeFull();

        HorizontalLayout header = new HorizontalLayout(new H1("Bookstore"), addButton());
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.BASELINE);
        header.setWidthFull();

        HorizontalLayout footer = new HorizontalLayout(updateButton(), deleteButton());
        footer.setJustifyContentMode(JustifyContentMode.END);
        footer.setAlignItems(Alignment.BASELINE);
        footer.setWidthFull();

        add(header, bookGrid, footer);
    }

    private void refreshGrid() {
//        bookGrid.setItems(VaadinSpringDataHelpers.fromPagingRepository(repo));
//        bookGrid.setItems(query -> service.findAll(
//                VaadinSpringDataHelpers.toSpringPageRequest(query)
//        ));

        allBooks = service.findAll();
        bookGrid.setItems(allBooks);

//        bookGrid.getDataProvider().refreshAll();
    }

    private Button deleteButton() {
        Button deleteBookButton = new Button("Delete Book", VaadinIcon.TRASH.create());
        deleteBookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        deleteBookButton.addClickListener(event -> {
            Set<Book> selected = bookGrid.getSelectedItems();
            if (selected.isEmpty()) {
                Notification.show("Make sure you have selected at least one book.");
            } else {
                deleteBookDialogContext(selected);
            }
        });
        return deleteBookButton;
    }

    private Button updateButton() {
        Button updateBookButton = new Button("Update Book", VaadinIcon.BOOK.create());
        updateBookButton.addClickListener(event -> {
            Set<Book> selected = bookGrid.getSelectedItems();
            if (selected.size() != 1) {
                Notification.show("Make sure you have selected a book.");
            } else {
                updateBookDialogContext(selected.stream().findFirst().orElseThrow());
            }
        });
        return updateBookButton;
    }

    private void updateBookDialogContext(Book selected) {
        openDialog(
                "Update Book",
                "Update",
                selected,
                (book) -> {
                    service.update(book);
                    refreshGrid();
                }
        );
    }

    private void deleteBookDialogContext(Set<Book> selected) {
        service.deleteAll(selected);
        refreshGrid();
    }

    private void deleteBookDialogContext(Book selected) {
        deleteBookDialogContext(Collections.singleton(selected));
    }

    private Button addButton() {
        Button addBookButton = new Button("Add Book", LumoIcon.PLUS.create());
        addBookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBookButton.addClickListener(event -> {
            openDialog(
                    "Add New Book",
                    "Add",
                    null,
                    (book) -> {
                        service.add(book);
                        refreshGrid();
                    }
            );
        });
        return addBookButton;
    }

    private void openDialog(
            String headerTitle,
            String actionButtonText,
            Book existing,
            ImplProvider<Book> onActionClick
    ) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(headerTitle);

        TextField titleField = new TextField("Title");
        DatePicker publishedAtField = new DatePicker("Published At");
        IntegerField ratingsField = new IntegerField("Ratings");
        ratingsField.setHelperText("Max 10 points");
        ratingsField.setMin(0);
        ratingsField.setMax(10);
        ratingsField.setValue(0);
        ratingsField.setStepButtonsVisible(true);

        Optional<Book> existingBook = Optional.ofNullable(existing);
        existingBook.ifPresent(value -> {
            titleField.setValue(value.getTitle());
            publishedAtField.setValue(value.getPublished());
            ratingsField.setValue(Integer.valueOf(value.getRating()));
        });

        VerticalLayout dialogLayout = createDialogLayout(titleField, publishedAtField, ratingsField);
        dialog.add(dialogLayout);

        Button actionButton = new Button(actionButtonText, event -> {
            onActionClick.execute(new Book(
                    existingBook.isPresent() ? existing.getId() : null,
                    titleField.getValue(),
                    publishedAtField.getValue(),
                    ratingsField.getValue().shortValue()
            ));
            dialog.close();
        });
        Button cancelButton = new Button("Cancel", event -> dialog.close());
        actionButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialog.getFooter().add(cancelButton, actionButton);
        dialog.open();
    }

    private VerticalLayout createDialogLayout(Component... components) {
        VerticalLayout dialogLayout = new VerticalLayout(components);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle()
                .set("width", "18rem")
                .set("max-width", "100%");

        return dialogLayout;
    }

    private void createGrid() {
        bookGrid = new Grid<>(Book.class, false);
        refreshGrid();

        bookGrid.addColumn(Book::getTitle).setHeader("Title").setAutoWidth(true).setSortable(true).setResizable(true).setFooter(allBooks.size() + " total books");
        bookGrid.addColumn(Book::getRating).setHeader("Ratings").setAutoWidth(true).setSortable(true).setResizable(true);
        bookGrid.addColumn(new LocalDateRenderer<>(
                Book::getPublished,
                () -> DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        )).setHeader("Published At").setAutoWidth(true).setSortable(true).setResizable(true);

        bookGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        GridMultiSelectionModel<Book> selectionModel = (GridMultiSelectionModel<Book>) bookGrid.getSelectionModel();
        selectionModel.setDragSelect(true);
        bookGrid.setRowsDraggable(true);
        bookGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        bookGrid.addComponentColumn(book -> {
            MenuBar menuBar = new MenuBar();
            menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
            menuBar.addItem("Preview", event -> updateBookDialogContext(book));
            menuBar.addItem("Edit", event -> updateBookDialogContext(book));
            menuBar.addItem("Delete", event -> deleteBookDialogContext(book));
            return menuBar;
        }).setWidth("70px").setFlexGrow(0);

        GridContextMenu<Book> menu = bookGrid.addContextMenu();
        menu.addItem("Preview", event -> event.getItem().ifPresent(this::updateBookDialogContext));
        menu.addItem("Edit", event -> event.getItem().ifPresent(this::updateBookDialogContext));
        menu.addItem("Delete", event -> event.getItem().ifPresent(this::deleteBookDialogContext));
    }
}
