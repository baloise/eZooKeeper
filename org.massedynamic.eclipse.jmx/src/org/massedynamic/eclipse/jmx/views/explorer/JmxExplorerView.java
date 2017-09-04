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

package org.massedynamic.eclipse.jmx.views.explorer;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import org.massedynamic.eclipse.core.actions.RefreshAction;
import org.massedynamic.eclipse.core.actions.BaseAction.InputType;
import org.massedynamic.eclipse.core.model.DataModelManager;
import org.massedynamic.eclipse.core.viewers.DataModelManagerElementType;
import org.massedynamic.eclipse.core.viewers.ElementTypes;
import org.massedynamic.eclipse.core.viewers.ViewerFactory;
import org.massedynamic.eclipse.jmx.JmxActivator;
import org.massedynamic.eclipse.jmx.actions.JmxDeleteAction;
import org.massedynamic.eclipse.jmx.actions.JmxOpenAction;
import org.massedynamic.eclipse.jmx.actions.NewJmxConnectionAction;
import org.massedynamic.eclipse.jmx.data.JmxConnection;
import org.massedynamic.eclipse.jmx.data.JmxConnectionDescriptor;
import org.massedynamic.eclipse.jmx.model.DomainModel;
import org.massedynamic.eclipse.jmx.model.JmxConnectionModel;
import org.massedynamic.eclipse.jmx.model.MBeanAttributeModel;
import org.massedynamic.eclipse.jmx.model.MBeanAttributesModelCategory;
import org.massedynamic.eclipse.jmx.model.MBeanModel;
import org.massedynamic.eclipse.jmx.model.MBeanOperationModel;
import org.massedynamic.eclipse.jmx.model.MBeanOperationsModelCategory;
import org.massedynamic.eclipse.jmx.model.ObjectNameKeyValueModel;
import org.massedynamic.eclipse.jmx.viewers.DomainModelElementType;
import org.massedynamic.eclipse.jmx.viewers.JmxConnectionModelElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanAttributeModelElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanAttributesModelCategoryElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanModelElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanOperationModelElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanOperationsModelCategoryElementType;
import org.massedynamic.eclipse.jmx.viewers.ObjectNameKeyValueModelElementType;

/**
 * 
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class JmxExplorerView extends ViewPart {

    public static final String ID = JmxExplorerView.class.getName();

    private JmxDeleteAction _DeleteAction;
    private NewJmxConnectionAction _NewJmxConnectionAction;
    private JmxOpenAction _OpenAction;
    private RefreshAction _RefreshAction;

    private TreeViewer _TreeViewer;

    private DrillDownAdapter _DrillDownAdapter;

    public JmxExplorerView() {
    }

    public void createPartControl(Composite parent) {

        DataModelManager<JmxConnectionModel, JmxConnectionDescriptor, JmxConnection> jmxConnectionModelManager = JmxActivator
                .getDefault().getJmxConnectionModelManager();

        ElementTypes elementTypes = new ElementTypes();

        elementTypes.add(jmxConnectionModelManager.getClass(), new DataModelManagerElementType());
        elementTypes.add(JmxConnectionModel.class, new JmxConnectionModelElementType());
        elementTypes.add(DomainModel.class, new DomainModelElementType());
        elementTypes.add(ObjectNameKeyValueModel.class, new ObjectNameKeyValueModelElementType());
        elementTypes.add(MBeanModel.class, new MBeanModelElementType());
        elementTypes.add(MBeanAttributesModelCategory.class, new MBeanAttributesModelCategoryElementType());
        elementTypes.add(MBeanAttributeModel.class, new MBeanAttributeModelElementType());
        elementTypes.add(MBeanOperationsModelCategory.class, new MBeanOperationsModelCategoryElementType());
        elementTypes.add(MBeanOperationModel.class, new MBeanOperationModelElementType());

        _TreeViewer = ViewerFactory.createDataModelTreeViewer(parent, SWT.MULTI | SWT.VIRTUAL | SWT.H_SCROLL
                | SWT.V_SCROLL, elementTypes, jmxConnectionModelManager);

        _TreeViewer.getTree().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent e) {
                getSite().getWorkbenchWindow().getActivePage().activate(JmxExplorerView.this);
            }
        });

        getSite().setSelectionProvider(_TreeViewer);

        _DrillDownAdapter = new DrillDownAdapter(_TreeViewer);

        makeActions();
        hookContextMenu();
        contributeToActionBars();

    }

    @Override
    public void init(IViewSite site) throws PartInitException {

        super.init(site);
        JmxActivator plugin = JmxActivator.getDefault();
        plugin.getJmxConnectionModelManager().getModels();
    }

    public void setFocus() {
        _TreeViewer.getControl().setFocus();
    }

    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillContextMenu(IMenuManager manager) {

        IStructuredSelection structuredSelection = (IStructuredSelection) _TreeViewer.getSelection();

        boolean emptySelection = structuredSelection == null || structuredSelection.isEmpty();

        manager.add(_NewJmxConnectionAction);

        if (emptySelection) {
            return;
        }

        manager.add(new Separator());
        manager.add(_OpenAction);
        manager.add(new Separator());

        if (_DeleteAction.isEnabled()) {
            manager.add(_DeleteAction);
            manager.add(new Separator());
        }

        manager.add(_RefreshAction);
        manager.add(new Separator());

        _DrillDownAdapter.addNavigationActions(manager);

        // Other plug-ins can contribute there actions here
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private void fillLocalPullDown(IMenuManager manager) {
        manager.add(_NewJmxConnectionAction);
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        manager.add(_NewJmxConnectionAction);

        manager.add(new Separator());
        _DrillDownAdapter.addNavigationActions(manager);
    }

    private void hookContextMenu() {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });

        Menu menu = menuMgr.createContextMenu(_TreeViewer.getControl());
        _TreeViewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, _TreeViewer);
    }

    private void makeActions() {

        _DeleteAction = new JmxDeleteAction();
        _DeleteAction.setSelectionProvider(_TreeViewer);

        _NewJmxConnectionAction = new NewJmxConnectionAction();
        _NewJmxConnectionAction.setSelectionProvider(_TreeViewer);

        _OpenAction = new JmxOpenAction();
        _OpenAction.setSelectionProvider(_TreeViewer);

        _RefreshAction = new RefreshAction(InputType.STRUCTURED_SELECTION);
        _RefreshAction.setSelectionProvider(_TreeViewer);
    }

}