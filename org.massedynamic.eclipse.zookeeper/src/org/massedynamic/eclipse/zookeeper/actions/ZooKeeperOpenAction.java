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

package org.massedynamic.eclipse.zookeeper.actions;

import org.eclipse.ui.IEditorInput;

import org.massedynamic.eclipse.jmx.actions.JmxOpenAction;
import org.massedynamic.eclipse.zookeeper.ZooKeeperActivator;
import org.massedynamic.eclipse.zookeeper.editors.znodeform.ZnodeModelEditorInput;
import org.massedynamic.eclipse.zookeeper.editors.znodeform.ZnodeModelFormEditor;
import org.massedynamic.eclipse.zookeeper.editors.zookeeperconnectionform.ZooKeeperConnectionModelEditorInput;
import org.massedynamic.eclipse.zookeeper.editors.zookeeperconnectionform.ZooKeeperConnectionModelFormEditor;
import org.massedynamic.eclipse.zookeeper.editors.zookeeperconnectionform.ZooKeeperConnectionModelServersFormPage;
import org.massedynamic.eclipse.zookeeper.editors.zookeeperserverform.ZooKeeperServerModelEditorInput;
import org.massedynamic.eclipse.zookeeper.editors.zookeeperserverform.ZooKeeperServerModelFormEditor;
import org.massedynamic.eclipse.zookeeper.model.ZnodeModel;
import org.massedynamic.eclipse.zookeeper.model.ZooKeeperConnectionModel;
import org.massedynamic.eclipse.zookeeper.model.ZooKeeperServerModel;
import org.massedynamic.eclipse.zookeeper.model.ZooKeeperServersModelCategory;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class ZooKeeperOpenAction extends JmxOpenAction {

    public ZooKeeperOpenAction() {
        addInputTypeClass(ZnodeModel.class);
        addInputTypeClass(ZooKeeperConnectionModel.class);
        addInputTypeClass(ZooKeeperServersModelCategory.class);
        addInputTypeClass(ZooKeeperServerModel.class);
    }

    @Override
    public void reportError(Exception e) {
        ZooKeeperActivator.reportError(e);
    }

    @Override
    protected String getAssociatedEditorId(Object object) {

        if (object instanceof ZnodeModel) {
            return ZnodeModelFormEditor.ID;
        }
        else if (object instanceof ZooKeeperConnectionModel) {
            return ZooKeeperConnectionModelFormEditor.ID;
        }
        else if (object instanceof ZooKeeperServerModel) {
            return ZooKeeperServerModelFormEditor.ID;
        }
        else if (object instanceof ZooKeeperServersModelCategory) {
            return ZooKeeperConnectionModelFormEditor.ID;
        }
        return super.getAssociatedEditorId(object);
    }

    @Override
    protected IEditorInput getAssociatedEditorInput(String editorId, Object object) {
        if (object instanceof ZnodeModel) {
            ZnodeModel model = (ZnodeModel) object;
            return new ZnodeModelEditorInput(editorId, model);
        }
        else if (object instanceof ZooKeeperConnectionModel) {
            ZooKeeperConnectionModel model = (ZooKeeperConnectionModel) object;
            return new ZooKeeperConnectionModelEditorInput(editorId, model);
        }
        else if (object instanceof ZooKeeperServerModel) {
            ZooKeeperServerModel model = (ZooKeeperServerModel) object;
            return new ZooKeeperServerModelEditorInput(editorId, model);
        }
        else if (object instanceof ZooKeeperServersModelCategory) {
            ZooKeeperConnectionModel model = ((ZooKeeperServersModelCategory) object).getParentModel();
            return new ZooKeeperConnectionModelEditorInput(editorId, model);
        }
        return super.getAssociatedEditorInput(editorId, object);
    }

    @Override
    protected String getAssociatedEditorPageId(String editorId, Object object) {
        if (object instanceof ZooKeeperServersModelCategory) {
            return ZooKeeperConnectionModelServersFormPage.ID;
        }

        return super.getAssociatedEditorPageId(editorId, object);
    }

}
