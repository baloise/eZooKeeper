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

package org.massedynamic.eclipse.zookeeper.editors.zookeeperserverform;

import org.massedynamic.eclipse.core.editors.DataModelEditorInput;
import org.massedynamic.eclipse.zookeeper.model.ZooKeeperServerModel;
import org.massedynamic.eclipse.zookeeper.viewers.ZooKeeperServerModelElementType;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class ZooKeeperServerModelEditorInput extends DataModelEditorInput<ZooKeeperServerModel> {

    public ZooKeeperServerModelEditorInput(String editorId, ZooKeeperServerModel zooKeeperServerModel) {
        super(editorId, zooKeeperServerModel, new ZooKeeperServerModelElementType());
    }

}
