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

package org.massedynamic.eclipse.jmx.actions;

import org.eclipse.ui.IEditorInput;

import org.massedynamic.eclipse.core.actions.BaseOpenAction;
import org.massedynamic.eclipse.jmx.editors.DomainMBeanModelManagerEditorInput;
import org.massedynamic.eclipse.jmx.editors.DomainMBeanModelTableEditor;
import org.massedynamic.eclipse.jmx.editors.JmxConnectionModelEditorInput;
import org.massedynamic.eclipse.jmx.editors.JmxConnectionModelFormEditor;
import org.massedynamic.eclipse.jmx.editors.MBeanAttributeModelEditorInput;
import org.massedynamic.eclipse.jmx.editors.MBeanAttributeModelFormEditor;
import org.massedynamic.eclipse.jmx.editors.MBeanModelAttributesFormPage;
import org.massedynamic.eclipse.jmx.editors.MBeanModelEditorInput;
import org.massedynamic.eclipse.jmx.editors.MBeanModelFormEditor;
import org.massedynamic.eclipse.jmx.editors.MBeanModelOperationsFormPage;
import org.massedynamic.eclipse.jmx.editors.MBeanOperationModelEditorInput;
import org.massedynamic.eclipse.jmx.editors.MBeanOperationModelFormEditor;
import org.massedynamic.eclipse.jmx.model.DomainModel;
import org.massedynamic.eclipse.jmx.model.JmxConnectionModel;
import org.massedynamic.eclipse.jmx.model.MBeanAttributeModel;
import org.massedynamic.eclipse.jmx.model.MBeanAttributesModelCategory;
import org.massedynamic.eclipse.jmx.model.MBeanModel;
import org.massedynamic.eclipse.jmx.model.MBeanOperationModel;
import org.massedynamic.eclipse.jmx.model.MBeanOperationsModelCategory;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class JmxOpenAction extends BaseOpenAction {

    public JmxOpenAction() {
        addInputTypeClass(DomainModel.class);
        addInputTypeClass(JmxConnectionModel.class);
        addInputTypeClass(MBeanAttributeModel.class);
        addInputTypeClass(MBeanOperationModel.class);
        addInputTypeClass(MBeanModel.class);
        addInputTypeClass(MBeanAttributesModelCategory.class);
        addInputTypeClass(MBeanOperationsModelCategory.class);
    }

    @Override
    protected String getAssociatedEditorId(Object object) {
        if (object instanceof MBeanModel) {
            return MBeanModelFormEditor.ID;
        }
        else if (object instanceof MBeanAttributeModel) {
            return MBeanAttributeModelFormEditor.ID;
        }
        else if (object instanceof MBeanOperationModel) {
            return MBeanOperationModelFormEditor.ID;
        }
        else if (object instanceof JmxConnectionModel) {
            return JmxConnectionModelFormEditor.ID;
        }
        else if (object instanceof DomainModel) {
            return DomainMBeanModelTableEditor.ID;
        }
        else if (object instanceof MBeanAttributesModelCategory) {
            return MBeanModelFormEditor.ID;
        }
        else if (object instanceof MBeanOperationsModelCategory) {
            return MBeanModelFormEditor.ID;
        }
        return null;
    }

    @Override
    protected IEditorInput getAssociatedEditorInput(String editorId, Object object) {
        if (object instanceof MBeanModel) {
            MBeanModel model = (MBeanModel) object;
            return new MBeanModelEditorInput(editorId, model);
        }
        else if (object instanceof MBeanAttributeModel) {
            MBeanAttributeModel model = (MBeanAttributeModel) object;
            return new MBeanAttributeModelEditorInput(editorId, model);
        }
        else if (object instanceof MBeanOperationModel) {
            MBeanOperationModel model = (MBeanOperationModel) object;
            return new MBeanOperationModelEditorInput(editorId, model);
        }
        else if (object instanceof JmxConnectionModel) {
            JmxConnectionModel model = (JmxConnectionModel) object;
            return new JmxConnectionModelEditorInput(editorId, model);
        }
        else if (object instanceof DomainModel) {
            DomainModel model = (DomainModel) object;
            return new DomainMBeanModelManagerEditorInput(editorId, model);
        }
        else if (object instanceof MBeanAttributesModelCategory) {
            MBeanModel model = ((MBeanAttributesModelCategory) object).getParentModel();
            return new MBeanModelEditorInput(editorId, model);
        }
        else if (object instanceof MBeanOperationsModelCategory) {
            MBeanModel model = ((MBeanOperationsModelCategory) object).getParentModel();
            return new MBeanModelEditorInput(editorId, model);
        }

        return null;
    }

    @Override
    protected String getAssociatedEditorPageId(String editorId, Object object) {
        if (object instanceof MBeanAttributesModelCategory) {
            return MBeanModelAttributesFormPage.ID;
        }
        else if (object instanceof MBeanOperationsModelCategory) {
            return MBeanModelOperationsFormPage.ID;
        }

        return super.getAssociatedEditorPageId(editorId, object);
    }

}
