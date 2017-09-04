/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.zookeeper.editors.znodeform;

import org.massedynamic.eclipse.core.actions.RefreshAction;
import org.massedynamic.eclipse.core.actions.BaseAction.InputType;
import org.massedynamic.eclipse.core.viewers.ElementTypes;
import org.massedynamic.eclipse.core.viewers.ViewerFactory;
import org.massedynamic.eclipse.zookeeper.ZooKeeperActivator;
import org.massedynamic.eclipse.zookeeper.actions.NewZnodeAction;
import org.massedynamic.eclipse.zookeeper.actions.TableEditAction;
import org.massedynamic.eclipse.zookeeper.actions.TableEditChildrenAction;
import org.massedynamic.eclipse.zookeeper.actions.ZooKeeperDeleteAction;
import org.massedynamic.eclipse.zookeeper.actions.ZooKeeperOpenAction;
import org.massedynamic.eclipse.zookeeper.data.Znode;
import org.massedynamic.eclipse.zookeeper.model.ZnodeModel;
import org.massedynamic.eclipse.zookeeper.viewers.ZnodeModelElementType;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IManagedForm;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class ZnodeModelChildrenFormPage extends BaseZnodeModelTableFormPage {

    public static final String ID = ZnodeModelChildrenFormPage.class.getName();
    public static final Image IMAGE = ZooKeeperActivator
            .getManagedImage(ZooKeeperActivator.IMAGE_KEY_OBJECT_ZNODE_CHILDREN);

    public static final String TITLE = "Children";

    private ZooKeeperDeleteAction _DeleteAction;

    private NewZnodeAction _NewZnodeAction;
    private ZooKeeperOpenAction _OpenAction;
    private RefreshAction _RefreshAction;
    private TableEditAction _TableEditAction;
    private TableEditChildrenAction _TableEditChildrenAction;
    private TableViewer _TableViewer;

    private ZnodeModelElementType _ZnodeModelElementType;

    /**
     * TODO: Comment.
     * 
     * @param editor
     * @param id
     * @param title
     */
    public ZnodeModelChildrenFormPage(ZnodeModelFormEditor editor) {
        super(editor, ID, TITLE, IMAGE);
        _ZnodeModelElementType = new ZnodeModelElementType();
    }

    @Override
    protected Table createTable(IManagedForm managedForm, Composite client) {

        final Table table = super.createTable(managedForm, client);
        table.setLinesVisible(false);
        ElementTypes elementTypes = new ElementTypes();

        elementTypes.add(ZnodeModel.class, _ZnodeModelElementType);

        _TableViewer = ViewerFactory.createDataModelTableViewer(table, elementTypes, null);

        makeTableActions();
        hookTableContextMenu();

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {

                getSite().getWorkbenchWindow().getActivePage().activate(getEditor());

                // if (table.getItem(new Point(e.x, e.y)) == null) {
                // _TableViewer.setSelection(null);
                // }
            }

        });

        return table;
    }

    @Override
    protected int[] getTableColumnAlignments() {
        return _ZnodeModelElementType.getColumnAlignments();
    }

    @Override
    protected String[] getTableColumnTitles() {
        return _ZnodeModelElementType.getColumnTitles();
    }

    @Override
    protected int[] getTableColumnWidths() {
        return _ZnodeModelElementType.getColumnWidths();
    }

    @Override
    protected int getTableStyle() {
        return SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI;
    }

    @Override
    protected void initTableItemsFromZnode() {

        ZnodeModel model = getModel();

        _TableViewer.setInput(model);
        setToolbarLabelText(Znode.STAT_NAME_CVERSION + ": " + model.getData().getStat().getCversion(),
                Znode.STAT_DESCRIPTION_CVERSION);
    }

    private void fillContextMenu(IMenuManager manager) {

        if (_TableViewer.getSelection().isEmpty()) {
            return;
        }

        manager.add(_NewZnodeAction);
        manager.add(new Separator());
        manager.add(_OpenAction);
        manager.add(new Separator());
        manager.add(_TableEditAction);
        manager.add(_TableEditChildrenAction);
        manager.add(new Separator());

        if (_DeleteAction.isEnabled()) {
            manager.add(_DeleteAction);
        }

        manager.add(new Separator());
        manager.add(_RefreshAction);
    }

    private void hookTableContextMenu() {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });

        Menu menu = menuMgr.createContextMenu(_TableViewer.getControl());
        _TableViewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, _TableViewer);
    }

    private void makeTableActions() {

        _DeleteAction = new ZooKeeperDeleteAction();
        _DeleteAction.setSelectionProvider(_TableViewer);

        _TableEditChildrenAction = new TableEditChildrenAction();
        _TableEditChildrenAction.setSelectionProvider(_TableViewer);

        _TableEditAction = new TableEditAction();
        _TableEditAction.setSelectionProvider(_TableViewer);

        _NewZnodeAction = new NewZnodeAction(InputType.SINGLE_STRUCTURED_SELECTION);
        _NewZnodeAction.setSelectionProvider(_TableViewer);

        _OpenAction = new ZooKeeperOpenAction();
        _OpenAction.setSelectionProvider(_TableViewer);

        _RefreshAction = new RefreshAction(InputType.STRUCTURED_SELECTION);
        _RefreshAction.setSelectionProvider(_TableViewer);

    }

}
